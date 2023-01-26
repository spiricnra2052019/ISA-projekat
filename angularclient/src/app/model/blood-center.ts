import { Address } from "./address";
import { BloodAmount } from "./blood-amount";
import { BloodCenterAdministrator } from "../components/blood-center-admin/blood-center-admin.component";

export class BloodCenter {
    id: number;
    averageRate: number;
    description: string;
    name: string;
    address: Address;
    bloodAmount: BloodAmount;
    bloodCenterAdministrator: BloodCenterAdministrator;
}
