export interface IRoom {
    id?: number;
    name?: string;
    bed?: number;
}

export class Room implements IRoom {
    constructor(public id?: number, public name?: string, public bed?: number) {}
}
