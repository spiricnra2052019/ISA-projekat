import { QueryQuestion } from "./query-question";
import { RegisteredUser } from "./registered-user";
import { UserToken } from "./user-token";

export class PatientAnswer {
    userId: number;
    question: QueryQuestion;
    answer: boolean;
}
