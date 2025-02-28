import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NativeControllerComponent } from './native-controller.component';

describe('NativeControllerComponent', () => {
  let component: NativeControllerComponent;
  let fixture: ComponentFixture<NativeControllerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NativeControllerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NativeControllerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
