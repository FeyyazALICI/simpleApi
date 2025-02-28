import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JpaControllerComponent } from './jpa-controller.component';

describe('JpaControllerComponent', () => {
  let component: JpaControllerComponent;
  let fixture: ComponentFixture<JpaControllerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JpaControllerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JpaControllerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
