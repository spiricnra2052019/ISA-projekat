import { Address } from "./address";
import { BloodCenterAdministrator } from "./blod-center-administrator";
import { BloodAmount } from "./blood-amount";

export class BloodCenter {
    id: number;
    averageRate: number;
    description: string;
    name: string;
    address: Address;
    bloodAmount: BloodAmount;
    bloodCenterAdministrator: BloodCenterAdministrator;
}
