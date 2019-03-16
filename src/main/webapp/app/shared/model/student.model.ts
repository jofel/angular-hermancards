import { IRoom } from 'app/shared/model/room.model';

export const enum Rank {
    STUDENT = 'STUDENT',
    KB = 'KB',
    SENIOR = 'SENIOR'
}

export interface IStudent {
    id?: number;
    name?: string;
    study?: string;
    rank?: Rank;
    room?: IRoom;
}

export class Student implements IStudent {
    constructor(public id?: number, public name?: string, public study?: string, public rank?: Rank, public room?: IRoom) {}
}
