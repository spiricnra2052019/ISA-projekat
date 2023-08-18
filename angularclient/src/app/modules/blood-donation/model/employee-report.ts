import { RegisteredUser } from "./registered-user";
import { ScheduleCalendar } from "./schedule-calendar";

export class EmployeeReport {
    bloodType: string;
    quantity: number;
    numberOfEquipmentUsed: number;
    description: string;
    price: number;
    user: RegisteredUser;
    appointment: ScheduleCalendar
}
