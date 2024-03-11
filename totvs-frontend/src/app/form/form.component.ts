import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  PoButtonModule,
  PoDynamicFormField,
  PoDynamicFormFieldChanged,
  PoDynamicFormValidation,
  PoDynamicModule,
  PoNotificationService,
} from '@po-ui/ng-components';
import { PhoneValidationService } from '../services/phone-validation.service';
import { Observable, Subscription, of } from 'rxjs';

@Component({
  standalone: true,
  selector: 'app-form',
  templateUrl: './form.component.html',
  imports: [PoDynamicModule, PoButtonModule],
})
export class FormComponent {
  @Input() isLoading = false;
  @Output() submitted = new EventEmitter();
  constructor(
    private http: HttpClient,
    public poNotification: PoNotificationService,
    private phoneValidationService: PhoneValidationService
  ) {}

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
    },
  ];

  formValue: any = {};

  validateFields = ['PhoneNumber'];

  validatePhone(changedValue: PoDynamicFormFieldChanged): Subscription {
    return this.phoneValidationService
      .validatePhone(changedValue.value.PhoneNumber)
      .subscribe((response) => {
        if (response) {
          return {
            fields: [
              {
                property: 'phone',
                pattern: undefined,
                errorMessage: '',
              },
            ],
          };
        }
        return {
          fields: [
            {
              property: 'phone',
              pattern: '/^(?!.*).*$/',
              errorMessage: 'Telefone já cadastrado',
            },
          ],
          focus: 'phone',
        };
      });
  }

  submitForm() {
    this.isLoading = true;
    const data = {
      name: this.formValue.Name,
      address: this.formValue.Address,
      neighborhood: this.formValue.Neighborhood,
      phoneNumberList: [this.formValue.PhoneNumber],
    };
    this.submitted.emit(data);
  }
}
