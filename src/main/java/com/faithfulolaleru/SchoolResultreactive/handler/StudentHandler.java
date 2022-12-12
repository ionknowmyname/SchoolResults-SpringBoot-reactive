package com.faithfulolaleru.SchoolResultreactive.handler;

import com.faithfulolaleru.SchoolResultreactive.dtos.StudentRequest;
import com.faithfulolaleru.SchoolResultreactive.dtos.StudentResponse;
import com.faithfulolaleru.SchoolResultreactive.exception.ErrorResponse;
import com.faithfulolaleru.SchoolResultreactive.exception.GeneralException;
import com.faithfulolaleru.SchoolResultreactive.models.Student;
import com.faithfulolaleru.SchoolResultreactive.repositories.StudentRepository;
import com.faithfulolaleru.SchoolResultreactive.utils.AppUtils;
import io.r2dbc.spi.Parameter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
//@AllArgsConstructor
@Slf4j
public record StudentHandler(StudentRepository studentRepository) {


    public Mono<ServerResponse> getAllStudents(ServerRequest request) {

        Flux<StudentResponse> response = studentRepository.findAll().map(AppUtils::entityToDto);

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(response, StudentResponse.class);
    }
    public Mono<ServerResponse> createStudent(ServerRequest request) {
        Mono<StudentRequest> studentRequestMono = request.bodyToMono(StudentRequest.class);


        // .doOnNext(result -> )
        Mono<StudentResponse> responseMono = studentRequestMono
                .map(req -> studentRepository.findStudentByName(req.getName())
                        .map(student -> throwErrorIfExist(student))
                        .switchIfEmpty(studentRepository.save(Student.builder()
                                .name(req.getName())
                                .studentClass(req.getStudentClass())
                                .build())))
                .flatMap(studentMono -> studentMono.map(s -> AppUtils.entityToDto(s)));
        //.subscribe();
        // log.info("Subscribe ---> ", subscribe);


       /* Mono<Student> entity = studentRequestMono.map(AppUtils::dtoToEntity);
        Mono<Student> savedStudentMono = studentRepository.findStudentByName(entity.block().getName())
                .map(student -> throwErrorIfExist(student))
                .switchIfEmpty(studentRepository.save(Student.builder()
                        .name(studentRequestMono.block().getName())
                        .studentClass(studentRequestMono.block().getStudentClass())
                        .build()));*/

        return ServerResponse.ok().body(responseMono, StudentResponse.class);
    }

    private StudentRequest returnRequest(StudentRequest req) {
        StudentRequest request = new StudentRequest();
        request.setName(req.getName());
        request.setStudentClass(req.getStudentClass());

        return request;
    }

    public Mono<ServerResponse> getStudentById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));

        Mono<Student> byId = studentRepository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return ServerResponse.ok().body(byId , StudentResponse.class)
                .switchIfEmpty(notFound);
        // or do custom error for not found with General exception
    }

    private Student  throwErrorIfExist(Student student) {
        String message = "Student with name '" + student.getName() + "' already exists";
        throw new GeneralException(HttpStatus.CONFLICT, ErrorResponse.ERROR_STUDENT_ALREADY_EXIST, message);
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

    public Mono<Student> findStudentByStudentId(Integer id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new GeneralException(HttpStatus.NOT_FOUND,
                        ErrorResponse.ERROR_STUDENT_NOT_EXIST,
                        "Student with id doesn't exist")));
    }

    public Mono<Student> findStudentByName(String name) {
        return studentRepository.findStudentByName(name)
                .switchIfEmpty(Mono.error(new GeneralException(HttpStatus.NOT_FOUND,
                        ErrorResponse.ERROR_STUDENT_NOT_EXIST,
                        "Student with name doesn't exist")));
    }
}
