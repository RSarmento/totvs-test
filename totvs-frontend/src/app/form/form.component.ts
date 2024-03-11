import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import {
  PoButtonModule,
  PoDynamicFormField,
  PoDynamicFormFieldChanged,
  PoDynamicFormValidation,
  PoDynamicModule,
  PoNotificationService
} from '@po-ui/ng-components';

@Component({
  standalone: true,
  selector: 'app-form',
  templateUrl: './form.component.html',
  imports: [PoDynamicModule, PoButtonModule],
})
export class FormComponent {
  constructor(
    private http: HttpClient,
    public poNotification: PoNotificationService
  ) {}

  isLoading = false;

  fields: PoDynamicFormField[] = [
    {
      property: 'Name',
      required: true,
      showRequired: true,
      minLength: 10,
      gridColumns: 12,
      label: 'Nome',
      errorMessage: 'O nome deve ter mais de 10 caracteres',
    },
    {
      property: 'Address',
      required: true,
      showRequired: true,
      gridColumns: 12,
      label: 'Endereço',
      errorMessage: 'O endereço é obrigatório',
    },
    {
      property: 'Neighborhood',
      required: true,
      showRequired: true,
      gridColumns: 12,
      label: 'Bairro',
      errorMessage: 'O bairro é obrigatório',
    },
    {
      property: 'PhoneNumber',
      required: true,
      showRequired: true,
      mask: '(99) 99999-9999',
      gridColumns: 12,
      label: 'Telefone',    
    }, // TODO: Deve ser uma lista de telefones e não deve permitir dois números iguais
  ];

  formValue: any = {};

  validateFields = ["PhoneNumber"]

  validatePhone(
    changedValue: PoDynamicFormFieldChanged
  ): Promise<PoDynamicFormValidation> {
    return new Promise((resolve) => {
      this.http.post(`http://localhost:8080/validate`, { number: changedValue.value.PhoneNumber })
      .subscribe({
        next: response => {
          if(response) {
            console.log("telefone válido")
            resolve({
              fields: [{
                property: 'phone',
                pattern: undefined,
                errorMessage: '',
              }],
            }); 
        } else {
            console.log("telefone inválido")
            resolve({
              fields: [{
                property: 'phone',
                pattern: '/^(?!.*).*$/',
                errorMessage: 'Telefone já cadastrado',
              }],
              focus: 'phone'
            })
          }
        },        
      })
  })
}
  

  submitForm() {
    this.isLoading = true;
    const data = {
      name: this.formValue.Name,
      address: this.formValue.Address,
      neighborhood: this.formValue.Neighborhood,
      phoneNumberList: [this.formValue.PhoneNumber],
    };
    this.http.post('http://localhost:8080/user', data).subscribe({
      next: response => {
        this.poNotification.success('Usuário cadastrado com sucesso');
        this.isLoading = false;
      },
      error: err => {
        this.poNotification.error('Erro ao cadastrar usuário');
        this.isLoading = false;
      },
    });
  }
}
