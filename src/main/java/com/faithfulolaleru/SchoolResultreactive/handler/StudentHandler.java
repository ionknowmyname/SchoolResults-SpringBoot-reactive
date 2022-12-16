package com.faithfulolaleru.SchoolResultreactive.handler;

import com.faithfulolaleru.SchoolResultreactive.dtos.StudentRequest;
import com.faithfulolaleru.SchoolResultreactive.dtos.StudentResponse;
import com.faithfulolaleru.SchoolResultreactive.exception.GeneralException;
import com.faithfulolaleru.SchoolResultreactive.exception.NotFoundException;
import com.faithfulolaleru.SchoolResultreactive.models.Student;
import com.faithfulolaleru.SchoolResultreactive.repositories.StudentRepository;
import com.faithfulolaleru.SchoolResultreactive.response.AppResponse;
import com.faithfulolaleru.SchoolResultreactive.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
//@AllArgsConstructor
@Slf4j
public record StudentHandler(StudentRepository studentRepository) {


    public Mono<ServerResponse> getAllStudents(ServerRequest request) {

        Mono<AppResponse> response = studentRepository.findAll()
                .map(AppUtils::entityToDto)
                .collectList()
                .flatMap(o -> AppUtils.buildAppResponse(o, "Successful"))
                .switchIfEmpty(Mono.empty());

        // without collectList() and Flux return type, it returns appResponse for each student
        // collectList() would collect all students into the data once, with just 1 complete AppResponse

        return ServerResponse.ok()
                // .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(response, AppResponse.class);
    }

    public Mono<ServerResponse> getAllStudentsByStudentClass(ServerRequest request) {

        String studentClass = request.pathVariable("studentClass");

        Mono<AppResponse> response = studentRepository.findStudentsByStudentClass(studentClass)
                .map(AppUtils::entityToDto)
                .collectList()
                .flatMap(o -> AppUtils.buildAppResponse(o, "Successful"))
                .switchIfEmpty(Mono.empty());

        return ServerResponse.ok().body(response, AppResponse.class);
    }

    public Mono<ServerResponse> getStudentById(ServerRequest request) {
        String id = request.pathVariable("id");

        Mono<AppResponse> byId = studentRepository.findById(id)
                .map(AppUtils::entityToDto)
                .flatMap(o -> AppUtils.buildAppResponse(o, "Successful"))
                .switchIfEmpty(Mono.error(new NotFoundException("Student with id not found")));


//                .onErrorResume(e -> Mono.error(new GeneralException(HttpStatus.NOT_FOUND,
//                        ErrorResponse.ERROR_STUDENT_NOT_EXIST,
//                        "Student with id not found")));  // , GeneralException.class

        // Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return ServerResponse.ok().body(byId, StudentResponse.class);
               // .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createStudent(ServerRequest request) {
        Mono<StudentRequest> studentRequestMono = request.bodyToMono(StudentRequest.class);

        Mono<AppResponse> responseMono = studentRequestMono
                .map(req -> studentRepository.findStudentByName(req.getName())
                        .map(student -> throwErrorIfExist(student))
                        .switchIfEmpty(studentRepository.save(Student.builder()
                                .name(req.getName())
                                .studentClass(req.getStudentClass())
                                .createdAt(LocalDateTime.now())
                                .build())))
                .flatMap(studentMono -> studentMono.map(s -> AppUtils.entityToDto2(s)))
                .flatMap(o -> AppUtils.buildAppResponse(o, "User Created Successfully"));  // update to return createdAt

        return ServerResponse.ok().body(responseMono, StudentResponse.class);
    }

    private StudentRequest returnRequest(StudentRequest req) {
        StudentRequest request = new StudentRequest();
        request.setName(req.getName());
        request.setStudentClass(req.getStudentClass());

        return request;
    }


    private Student  throwErrorIfExist(Student student) {
        String message = "Student with name '" + student.getName() + "' already exists";
        throw new GeneralException(HttpStatus.CONFLICT, message);
    }

    private Mono<Student> saveStudent(Student student) {
        return studentRepository.save(student);
    }

    private Mono<Student> saveStudent(StudentRequest request) {
        return studentRepository.save(Student.builder()
                .name(request.getName())
                .studentClass(request.getStudentClass())
                .build());
    }

    public Mono<Student> findStudentByStudentId(String id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new GeneralException(HttpStatus.NOT_FOUND,
                        "Student with id doesn't exist")));
    }

    public Mono<Student> findStudentByName(String name) {
        return studentRepository.findStudentByName(name)
                .switchIfEmpty(Mono.error(new GeneralException(HttpStatus.NOT_FOUND,
                        "Student with name doesn't exist")));
    }

}
