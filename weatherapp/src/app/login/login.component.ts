import { Component, EventEmitter, Output } from '@angular/core';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AppService } from '../app.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm: FormGroup;
  @Output() submitSuccess = new EventEmitter<void>();
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private appService: AppService) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) return;

    const { username, password } = this.loginForm.value;

    this.appService.login(username, password).subscribe({
      next: (authToken: string) => {
        //if you used JWT, please use Bearer instead of Basic!
        localStorage.setItem('auth', `Basic ${authToken}`);
        this.submitSuccess.emit();
      },
      error: (err) => {
        if (err.status === 401) {
          this.errorMessage = 'Invalid username or password.';
        } else {
          this.errorMessage = 'An error occurred. Please try again.';
        }
      },
    });
  }
}
