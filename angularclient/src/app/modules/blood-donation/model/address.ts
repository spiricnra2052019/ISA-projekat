export class Address {
  id: number;
  street: string;
  streetNumber: number;
  city: string;
  country: string;

  public constructor() {
    this.id = null as any;
    this.street = '';
    this.streetNumber = null as any;
    this.city = '';
    this.country = '';
  }
}
