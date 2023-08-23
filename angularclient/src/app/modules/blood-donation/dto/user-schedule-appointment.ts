import { Time } from "@angular/common";

export class UserScheduleAppointmentDTO {
    userId: number;
    bloodCenterId: number;
    scheduleDate: Date;
    startTime: Time;
    duration: number;
}