import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMotoristaComponent } from './add-motorista.component';

describe('AddMotoristaComponent', () => {
  let component: AddMotoristaComponent;
  let fixture: ComponentFixture<AddMotoristaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMotoristaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMotoristaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
