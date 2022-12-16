package com.faithfulolaleru.SchoolResultreactive.handler;

import com.faithfulolaleru.SchoolResultreactive.dtos.ScoreRequest;
import com.faithfulolaleru.SchoolResultreactive.dtos.ScoreResponse;
import com.faithfulolaleru.SchoolResultreactive.exception.GeneralException;
import com.faithfulolaleru.SchoolResultreactive.exception.NotFoundException;
import com.faithfulolaleru.SchoolResultreactive.handler.service.StudentService;
import com.faithfulolaleru.SchoolResultreactive.models.Score;
import com.faithfulolaleru.SchoolResultreactive.repositories.ScoreRepository;
import com.faithfulolaleru.SchoolResultreactive.response.AppResponse;
import com.faithfulolaleru.SchoolResultreactive.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Slf4j
public record ScoreHandler(ScoreRepository scoreRepository, StudentService studentService) {
    public Mono<ServerResponse> createScoreForStudent(ServerRequest request) {

        String studentId = request.pathVariable("studentId");
        Mono<ScoreRequest> scoreRequestMono = request.bodyToMono(ScoreRequest.class);

        Mono<AppResponse> responseMono = scoreRequestMono
                .map(req -> studentService.findStudentById(studentId)
                        .flatMap(student -> scoreRepository.findByStudentIdAndTerm(student.getId(), req.getTerm())
                                .map(score -> throwErrorIfExist(score))
                                .switchIfEmpty(scoreRepository.save(scoreBuilder(req, studentId)))))
                .flatMap(scoreMono -> scoreMono.map(s -> {
                    ScoreResponse sr = AppUtils.entityToDto(s);
                    sr.setCreatedAt(s.getCreatedAt());   // unnecessary bt keep for when you wanna add to responseDto

                    return sr;
                }))
                .flatMap(o -> AppUtils.buildAppResponse(o, "Score Created Successfully"));

        return ServerResponse.ok().body(responseMono, ScoreResponse.class);
    }

    public Mono<ServerResponse> updateScoreForStudent(ServerRequest request) {

        String studentId = request.pathVariable("studentId");
        Mono<ScoreRequest> scoreRequestMono = request.bodyToMono(ScoreRequest.class);

        Mono<AppResponse> responseMono = scoreRequestMono
                .map(req -> studentService.findStudentById(studentId)
                        .flatMap(student -> scoreRepository.findByStudentIdAndTerm(student.getId(), req.getTerm())
                                .flatMap(score -> updateScore(req, score))
                                .map(score -> AppUtils.entityToDto(score))
                                .flatMap(o -> AppUtils.buildAppResponse(o, "Updated Score Successfully"))
                                .switchIfEmpty(Mono.error(new GeneralException(HttpStatus.NOT_FOUND,
                                        "Student has no score for selected Term")))))
                .flatMap(m -> Mono.from(m));


        return ServerResponse.ok().body(responseMono, ScoreResponse.class);
    }

    public Mono<ServerResponse> getAllScoresByStudentId(ServerRequest request) {

        String studentId = request.pathVariable("studentId");

        Mono<AppResponse> response = scoreRepository.findAllByStudentId(studentId)
                .map(AppUtils::entityToDto)
                .collectList()
                .flatMap(o -> AppUtils.buildAppResponse(o, "Successful"))
                .switchIfEmpty(Mono.empty());

        return ServerResponse.ok().body(response, AppResponse.class);
    }

    public Mono<ServerResponse> deleteScoreByStudentIdAndTerm(ServerRequest request) {

        String studentId = request.pathVariable("studentId");
        Integer term = Integer.valueOf(request.pathVariable("term"));

        Mono<AppResponse> deleted = scoreRepository.findByStudentIdAndTerm(studentId, term)
                .flatMap(score -> scoreRepository.delete(score).thenReturn(score))
                .map(score -> AppUtils.entityToDto(score))
                .flatMap(o -> AppUtils.buildAppResponse(o, "Deleted Score Successfully"))
                .switchIfEmpty(Mono.error(new NotFoundException("Selected Student has no score for selected term")));

        return ServerResponse.ok().body(deleted, AppResponse.class);
    }

    private Score throwErrorIfExist(Score score) {
        String message = "Score for term '" + score.getTerm() + "' already exists for student";
        throw new GeneralException(HttpStatus.CONFLICT, message);
    }

    private Score scoreBuilder(ScoreRequest req, String studentId) {
        return Score.builder()
                .studentId(studentId)
                .term(req.getTerm())
                .subject1Score(req.getSubject1Score())
                .subject2Score(req.getSubject2Score())
                .subject3Score(req.getSubject3Score())
                .subject4Score(req.getSubject4Score())
                .subject5Score(req.getSubject5Score())
                .subject6Score(req.getSubject6Score())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private Mono<Score> updateScore(ScoreRequest req, Score score) {
        Score newScore = Score.builder()
                .id(score.getId())
                .studentId(score.getStudentId())
                .term(score.getTerm())
                .subject1Score((req.getSubject1Score()) != null ? req.getSubject1Score() : score.getSubject1Score())
                .subject2Score((req.getSubject2Score()) != null ? req.getSubject2Score() : score.getSubject2Score())
                .subject3Score((req.getSubject3Score()) != null ? req.getSubject3Score() : score.getSubject3Score())
                .subject4Score((req.getSubject4Score()) != null ? req.getSubject4Score() : score.getSubject4Score())
                .subject5Score((req.getSubject5Score()) != null ? req.getSubject5Score() : score.getSubject5Score())
                .subject6Score((req.getSubject6Score()) != null ? req.getSubject6Score() : score.getSubject6Score())
                .createdAt(score.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        return scoreRepository.save(newScore);
    }

}
