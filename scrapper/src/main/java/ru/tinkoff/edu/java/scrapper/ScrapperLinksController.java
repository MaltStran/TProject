package ru.tinkoff.edu.java.scrapper;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping("/links")
@RestController
public class ScrapperLinksController {
    @ApiResponse(responseCode = "200", description = "Чат зарегистрирован")
    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    @ApiResponse(responseCode = "404", description = "Ссылка не найдена")
    @GetMapping
    ListLinksResponse getLinks(@RequestHeader int id){
        return new ListLinksResponse(new LinkResponse[1], 12);
    }
    @PostMapping
    AddLinkRequest addLinkRequest(@RequestHeader int id) throws URISyntaxException {
        return new AddLinkRequest(new URI("add"));
    }
    @DeleteMapping
    RemoveLinkRequest deletLink(@RequestHeader int id) throws URISyntaxException {
        return new RemoveLinkRequest(new URI("delete"));
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleException(MethodArgumentNotValidException e){
        return new ApiErrorResponse("Некорректные параметры запроса",
                e.getStatusCode().toString(),
                e.getObjectName(),
                e.getLocalizedMessage(),
                new String[]{String.valueOf(e.getStackTrace())});
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse linkException(MethodArgumentNotValidException e){
        return new ApiErrorResponse("Ссылка не найдена",
                e.getStatusCode().toString(),
                e.getObjectName(),
                e.getLocalizedMessage(),
                new String[]{String.valueOf(e.getStackTrace())});
    }

}
