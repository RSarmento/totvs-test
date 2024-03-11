import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
})
export class SubmitFormService {
    constructor (private httpClient: HttpClient){}

    submit(formData: FormData ){
        return this.httpClient.post('http://localhost:8080/user', formData)
    }
}