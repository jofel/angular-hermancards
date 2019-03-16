import { Moment } from 'moment';
import { IStudent } from 'app/shared/model/student.model';

export interface ICard {
    id?: number;
    image?: string;
    createdDate?: Moment;
    losted?: boolean;
    student?: IStudent;
}

export class Card implements ICard {
    constructor(
        public id?: number,
        public image?: string,
        public createdDate?: Moment,
        public losted?: boolean,
        public student?: IStudent
    ) {
        this.losted = this.losted || false;
    }
}
