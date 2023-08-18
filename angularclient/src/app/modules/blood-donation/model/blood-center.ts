import { Address } from "./address";
import { BloodCenterAdministrator } from "./blod-center-administrator";
import { BloodAmount } from "./blood-amount";
import { WorkingTime } from "./working-time";

export class BloodCenter {
    id: number;
    averageRate: number;
    description: string;
    name: string;
    address: Address;
    bloodAmount: BloodAmount;
    bloodCenterAdministrator: BloodCenterAdministrator;
    workingTime: WorkingTime;
}
