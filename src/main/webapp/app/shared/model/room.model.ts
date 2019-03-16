export interface IRoom {
    id?: number;
    name?: string;
    size?: number;
}

export class Room implements IRoom {
    constructor(public id?: number, public name?: string, public size?: number) {}
}
