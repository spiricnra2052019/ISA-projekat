import { Address } from './address';

export class RegisteredUser {
  id: string;
  name: string;
  lastname: string;
  username: string;
  password: string;
  birthday: string;
  address: Address;

  public constructor() {
    this.id = '';
    this.name = '';
    this.birthday = '';
    this.id = '';
    this.lastname = '';
    this.username = '';
    this.address = new Address();
  }
}
