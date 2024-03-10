import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import {
  PoButtonModule,
  PoDynamicFormField,
  PoDynamicFormFieldChanged,
  PoDynamicFormFieldValidation,
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
      property: 'name',
      required: true,
      showRequired: true,
      minLength: 10,
      gridColumns: 12,
      label: 'Nome',
      errorMessage: 'O nome deve ter mais de 10 caracteres',
    },
    {
      property: 'address',
      required: true,
      showRequired: true,
      gridColumns: 12,
      label: 'Endereço',
      errorMessage: 'O endereço é obrigatório',
    },
    {
      property: 'neighborhood',
      required: true,
      showRequired: true,
      gridColumns: 12,
      label: 'Bairro',
      errorMessage: 'O bairro é obrigatório',
    },
    {
      property: 'phone',
      required: true,
      showRequired: true,
      mask: '(99) 99999-9999',
      gridColumns: 12,
      label: 'Telefone',
      validate: this.validatePhone.bind(this),
    }, // TODO: Deve ser uma lista de telefones e não deve permitir dois números iguais
  ];

  formValue: any = {};

  async validatePhone(
    changedValue: PoDynamicFormFieldChanged
  ): Promise<PoDynamicFormFieldValidation> {
    const isPhoneAvailable = await this.http
      .post(`http://localhost:8080/validate`, { number: changedValue.value })
      .toPromise()
      .then(() => true)
      .catch(() => false);
    console.log(isPhoneAvailable);
    if (!isPhoneAvailable) {
      return {
        value: changedValue.value,
        field: {
          property: 'phone',
          pattern: '/^(?!.*).*$/',
          errorMessage: 'Telefone já cadastrado',
        },
        focus: true,
      };
    }
    return {
      value: changedValue.value,
      field: {
        property: 'phone',
        pattern: undefined,
        errorMessage: '',
      },
    };
  }

  submitForm() {
    this.isLoading = true;
    const data = {
      name: this.formValue.name,
      address: this.formValue.address,
      neighborhood: this.formValue.neighborhood,
      phoneNumberList: [this.formValue.phone],
    };
    this.http.post('http://localhost:8080/user', data).subscribe({
      next: response => {
        this.poNotification.success('Usuário cadastrado com sucesso');
        this.isLoading = false;
        console.log(response);
      },
      error: err => {
        this.poNotification.error('Erro ao cadastrar usuário');
        this.isLoading = false;
        console.log(err)
      },
    });
  }
}
