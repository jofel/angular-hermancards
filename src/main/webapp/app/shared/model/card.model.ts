import { Moment } from 'moment';
import { IStudent } from 'app/shared/model/student.model';

export interface ICard {
    id?: number;
    createdDate?: Moment;
    losted?: boolean;
    active?: boolean;
    student?: IStudent;
}

export class Card implements ICard {
    constructor(
        public id?: number,
        public createdDate?: Moment,
        public losted?: boolean,
        public active?: boolean,
        public student?: IStudent
    ) {
        this.losted = this.losted || false;
        this.active = this.active || false;
    }
}
