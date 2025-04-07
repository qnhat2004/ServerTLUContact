import { IUser } from 'app/shared/model/user.model';
import { IUnit } from 'app/shared/model/unit.model';

export interface IStaff {
  id?: number;
  staffId?: string;
  fullName?: string;
  position?: string | null;
  phone?: string | null;
  address?: string | null;
  education?: string | null;
  user?: IUser | null;
  unit?: IUnit | null;
}

export const defaultValue: Readonly<IStaff> = {};
