import { RegisteredUser } from '../model/registered-user';

export class AppointmentDTO {
  description: string;
  bloodType: string;
  quantity: number;
  numberOfEquipments: number;
  patientId: number;
  scheduleCalendarId: number;

  public constructor() {
    this.bloodType = 'A';
    this.description = '';
    this.quantity = 0;
    this.numberOfEquipments = 0;
    this.patientId = 0;
    this.scheduleCalendarId = 0;
  }
}
