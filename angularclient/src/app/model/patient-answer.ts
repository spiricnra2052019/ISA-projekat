import {QueryQuestion} from "./query-question";
import {RegisteredUser} from "./registered-user";

export class PatientAnswer {
    registeredUser: RegisteredUser;
    patientQuestion: QueryQuestion;
    answer: boolean;
}
