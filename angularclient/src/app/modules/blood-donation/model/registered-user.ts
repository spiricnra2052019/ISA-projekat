import { Address } from "./address";

export class RegisteredUser {
    id: string;
    name: string;
    lastname: string;
    username: string;
    password: string;
    birthday: string;
    address: Address;
}