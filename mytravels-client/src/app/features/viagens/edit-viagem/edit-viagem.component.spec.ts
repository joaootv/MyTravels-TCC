import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditViagemComponent } from './edit-viagem.component';

describe('EditViagemComponent', () => {
  let component: EditViagemComponent;
  let fixture: ComponentFixture<EditViagemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditViagemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditViagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
