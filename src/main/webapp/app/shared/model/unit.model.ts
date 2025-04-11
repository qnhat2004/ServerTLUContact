import { UnitType } from 'app/shared/model/enumerations/unit-type.model';

export interface IUnit {
  id?: number;
  unitCode?: string;
  name?: string;
  address?: string | null;
  logoUrl?: string | null;
  email?: string | null;
  fax?: string | null;
  type?: keyof typeof UnitType;
  parentUnit?: IUnit | null;
}

export const defaultValue: Readonly<IUnit> = {};
