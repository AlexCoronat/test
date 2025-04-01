import { ChangeDetectionStrategy, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { InventoryService } from './inventory.service';
import { CommonModule } from '@angular/common';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, NgForm } from '@angular/forms';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from '../../../auth/auth.service';
import { MatDialogContent, MatDialogActions, MatDialogClose, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-inventory',
  imports: [ MatTableModule, MatSelectModule, CommonModule, MatFormFieldModule, FormsModule, MatSlideToggleModule, MatInputModule, MatButtonModule],
  standalone: true,
  templateUrl: './inventory.component.html',
  styleUrl: './inventory.component.css'
})
export class InventoryComponent implements OnInit {
  @ViewChild('productForm') productForm!: NgForm;
  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>([]);
  displayedColumns: string[] = ['name', 'price', 'stock','status']
  permissions: string[] = [];
  editingProduct: any = null;
  originalProduct: any = null;
  message: string = '';
  newProduct: any = {
    name: '',
    price: 0,
    stock: 0,
    status: true
  };
  constructor(
    private authServise: AuthService,
    private inventoryService: InventoryService,
    private dialog: MatDialog
  ) { }
  ngOnInit(): void {
    this.getInventory();
    this.permissions = this.authServise.getPermissions();
  }
  onModelChange(newValue: number): void {
    if(this.editingProduct) {
      if(newValue < this.originalProduct.stock && !this.permissions.includes('SI')) {
        this.openRestrictionDialog('No tiene permisos para disminuir el stock');
        this.productForm.form.setErrors({ insufficientPermission: true });
      }
    }else{
      if (newValue <= 0) {
        this.openRestrictionDialog('El stock debe ser mayor a 0');
        this.productForm.form.setErrors({ invalidStock: true });
      }
    }
  }
  openRestrictionDialog(message: string): void {
    this.dialog.open(RestrictionDialog, {
      data: { message }
    });
  }
  selectProduct(product: any) {
    this.editingProduct = product.idProduct;
    this.originalProduct = { ...product };
    this.newProduct = { ...product };
  }
  cancelEdit() {
    this.editingProduct = null;
    this.newProduct = { name: '', price: 0, stock: 0, status: true };
    if (this.originalProduct) {
      const index = this.dataSource.data.findIndex(p => p.idProduct === this.originalProduct.idProduct);
      if (index > -1) {
        this.dataSource.data[index] = this.originalProduct;
        this.dataSource._updateChangeSubscription();
      }
    }
  }
  onSubmit() {
    console.log(this.permissions)
    if (this.editingProduct) {
      this.inventoryService.updateProduct(this.editingProduct, this.newProduct)
        .subscribe({
          next: () => {
            this.getInventory();
            this.cancelEdit();
          },
          error: (error) => {
            console.error('Error actualizando producto:', error);
            this.cancelEdit();
          }
        });
    } else {
      this.inventoryService.createProduct(this.newProduct)
        .subscribe({
          next: () => {
            this.getInventory();
            this.newProduct = { name: '', price: 0, stock: 0, status: true };
          },
          error: (error) => console.error('Error creando producto:', error)
        });
    }
  }
  getInventory() {
    this.inventoryService.getInventory().subscribe(
      (data: any) => {
        this.dataSource = new MatTableDataSource<any>(data);
      },
      (error: any) => {
        console.error('Error fetching inventory:', error);
      }
    );
  }
}
@Component({
  selector: 'restriction-dialog',
  template: `
    <mat-dialog-content>{{data.message}}</mat-dialog-content>
    <mat-dialog-actions>
      <button mat-button mat-dialog-close>Cerrar</button>
    </mat-dialog-actions>
  `,
  imports: [ MatDialogContent, MatDialogActions, MatDialogClose, MatButtonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RestrictionDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string }) {}
}
