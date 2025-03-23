import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewcontrollerComponent } from './viewcontroller.component';

describe('ViewcontrollerComponent', () => {
  let component: ViewcontrollerComponent;
  let fixture: ComponentFixture<ViewcontrollerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewcontrollerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewcontrollerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
