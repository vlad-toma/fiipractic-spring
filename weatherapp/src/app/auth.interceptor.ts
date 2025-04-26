import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = localStorage.getItem('auth');
  if (auth) {
    req = req.clone({
      setHeaders: {
        Authorization: auth,
      },
    });
  }
  return next(req);
};
