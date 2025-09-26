import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekDialogComponent } from './week-dialog.component';

describe('WeekDialogComponent', () => {
  let component: WeekDialogComponent;
  let fixture: ComponentFixture<WeekDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WeekDialogComponent]
    });
    fixture = TestBed.createComponent(WeekDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
