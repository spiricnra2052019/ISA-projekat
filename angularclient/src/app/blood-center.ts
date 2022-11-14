import { Address } from "./address";
import { BloodAmount } from "./blood-amount";

export class BloodCenter {
    id: string;
    averageRate: string;
    description: string;
    name: string;
    address: Address;
    bloodAmount: BloodAmount;
}
