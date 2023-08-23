import { idText } from "typescript";
import { Address } from "./address";

export class EditRegisteredUser {
    constructor(id: string, name: string, lastname: string, username: string, password: string, birthday: string, address: Address) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
    }

    id: string;
    name: string;
    lastname: string;
    username: string;
    password: string;
    birthday: string;
    address: Address;
}