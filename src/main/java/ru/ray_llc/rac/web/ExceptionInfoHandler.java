package ru.ray_llc.rac.web;

import static ru.ray_llc.rac.util.ValidationUtil.getInfoFromBindingResult;
import static ru.ray_llc.rac.util.exception.ErrorType.APP_ERROR;
import static ru.ray_llc.rac.util.exception.ErrorType.DATA_ERROR;
import static ru.ray_llc.rac.util.exception.ErrorType.DATA_NOT_FOUND;
import static ru.ray_llc.rac.util.exception.ErrorType.VALIDATION_ERROR;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.ray_llc.rac.util.ValidationUtil;
import ru.ray_llc.rac.util.exception.ErrorInfo;
import ru.ray_llc.rac.util.exception.ErrorType;
import ru.ray_llc.rac.util.exception.IllegalRequestDataException;
import ru.ray_llc.rac.util.exception.NotFoundException;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {

  private static final Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

  @Autowired
  private MessageSource messageSource;

  //    https://stackoverflow.com/questions/538870/should-private-helper-methods-be-static-if-they-can-be-static
  private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e,
      boolean logException, ErrorType errorType) {
    Throwable rootCause = ValidationUtil.getRootCause(e);
    if (logException) {
      log.error(errorType + " at request " + req.getRequestURL(), rootCause);
    } else {
      log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
    }
    return new ErrorInfo(req.getRequestURL(), errorType, rootCause.toString());
  }

  //  http://stackoverflow.com/a/22358422/548473
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(NotFoundException.class)
  public ErrorInfo handleError(HttpServletRequest req, NotFoundException e) {
    return logAndGetErrorInfo(req, e, false, DATA_NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.CONFLICT)  // 409
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
    ErrorInfo errorInfo = logAndGetErrorInfo(req, e, true, DATA_ERROR);
    Throwable rootCause = ValidationUtil.getRootCause(e);
    if (rootCause instanceof PSQLException) {
      String errorCode = ((PSQLException) rootCause).getSQLState();
      if ("23505".equals(errorCode)) {
        String message = rootCause.getMessage();
        if (message.contains("users_unique_login_idx")) {
          errorInfo.setDetail(messageSource
              .getMessage("exception.unique_mail", new Object[]{"User unique login"},
                  Locale.getDefault()));
        }
      }
    }
    return errorInfo;
  }

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  // 422
  @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class,
      IllegalRequestDataException.class, MethodArgumentTypeMismatchException.class,
      HttpMessageNotReadableException.class})
  public ErrorInfo illegalRequestDataError(HttpServletRequest req, Exception e) {
    ErrorInfo errorInfo = logAndGetErrorInfo(req, e, false, VALIDATION_ERROR);
    if (e instanceof BindException) {
      BindingResult bindingResult = ((BindException) e).getBindingResult();
      String info = getInfoFromBindingResult(bindingResult);
      errorInfo.setDetail(info);
    }
    if (e instanceof MethodArgumentNotValidException) {
      BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
      String info = getInfoFromBindingResult(bindingResult);
      errorInfo.setDetail(info);
    }
    return errorInfo;
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorInfo handleError(HttpServletRequest req, Exception e) {
    return logAndGetErrorInfo(req, e, true, APP_ERROR);
  }
}