import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {
  PoMenuItem,
  PoMenuModule,
  PoNotification,
  PoNotificationService,
  PoPageModule,
  PoToolbarModule,
} from '@po-ui/ng-components';
import { FormComponent } from './form/form.component';
import { SubmitFormService } from './services/submit-form.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    PoToolbarModule,
    PoMenuModule,
    PoPageModule,
    HttpClientModule,
    FormComponent,
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  isLoading = false;

  readonly menus: Array<PoMenuItem> = [
    { label: 'Cadastro de usuário', action: this.onClick.bind(this) },
  ];

  constructor(
    private submitFormService: SubmitFormService,
    private poNotification: PoNotificationService
  ) {}

  private onClick() {
    alert('Clicked in menu item');
  }

  submitFormValue(event: FormData) {
    this.isLoading = true;
    this.submitFormService.submit(event).subscribe({
      next: (response) => {
        this.poNotification.success('Usuário cadastrado com sucesso');
        this.isLoading = false;
      },
      error: (err) => {
        this.poNotification.error('Erro ao cadastrar usuário');
        this.isLoading = false;
      },
    });
  }
}
