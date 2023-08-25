import { BloodCenter } from './blood-center';
import { RegisteredUser } from './registered-user';

export class ScheduleCalendar {
  id: string;
  scheduleDate: string;
  startTime: string;
  duration: number;
  user: RegisteredUser;
  bloodCenterId: number;
  bloodCenter: BloodCenter;
}
