import { IUser } from 'app/shared/model/user.model';
import { IUnit } from 'app/shared/model/unit.model';

export interface IStudent {
  id?: number;
  studentId?: string;
  fullName?: string;
  phone?: string | null;
  address?: string | null;
  email?: string | null;
  avatarUrl?: string | null;
  user?: IUser | null;
  unit?: IUnit | null;
}

export const defaultValue: Readonly<IStudent> = {};
