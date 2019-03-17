import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { ICard } from 'app/shared/model/card.model';
import { CardService } from './card.service';
import { IStudent } from 'app/shared/model/student.model';
import { StudentService } from 'app/entities/student';

@Component({
    selector: 'jhi-card-update',
    templateUrl: './card-update.component.html'
})
export class CardUpdateComponent implements OnInit {
    card: ICard;
    isSaving: boolean;

    students: IStudent[];
    createdDateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cardService: CardService,
        protected studentService: StudentService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ card }) => {
            this.card = card;
        });
        this.studentService
            .query({ 'cardId.specified': 'false' })
            .pipe(
                filter((mayBeOk: HttpResponse<IStudent[]>) => mayBeOk.ok),
                map((response: HttpResponse<IStudent[]>) => response.body)
            )
            .subscribe(
                (res: IStudent[]) => {
                    if (!this.card.student || !this.card.student.id) {
                        this.students = res;
                    } else {
                        this.studentService
                            .find(this.card.student.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IStudent>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IStudent>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IStudent) => (this.students = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.card.id !== undefined) {
            this.subscribeToSaveResponse(this.cardService.update(this.card));
        } else {
            this.subscribeToSaveResponse(this.cardService.create(this.card));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICard>>) {
        result.subscribe((res: HttpResponse<ICard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackStudentById(index: number, item: IStudent) {
        return item.id;
    }
}
