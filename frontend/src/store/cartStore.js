import { defineStore } from "pinia";

export const useCartStore = defineStore("cartStore", {
  state: () => ({
    items: [],
  }),
  getters: {
    totalPrice: (state) => {
      return state.items.reduce(
        (total, item) => total + item.price * item.quantity,
        0
      );
    },
    totalItems: (state) => {
      return state.items.reduce((total, item) => total + item.quantity, 0);
    },
  },
  actions: {
    addItem(product) {
      const existingItem = this.items.find((item) => item.id === product.id);
      if (existingItem) {
        existingItem.quantity += 1;
      } else {
        this.items.push({ ...product, quantity: 1 });
      }
    },
    removeItem(productId) {
      this.items = this.items.filter((item) => item.id !== productId);
    },
    updateQuantity(productId, quantity) {
      const item = this.items.find((item) => item.id === productId);
      if (item) {
        item.quantity = quantity;
        this.updateCartItems();
      }
    },
    updateCartItems() {
      this.items = this.items.filter((item) => item.quantity > 0);
    },
    clearCart() {
      this.items = [];
    },
    itemQuantityByName(name) {
      const item = this.items.find((item) => item.name === name);
      return item ? item.quantity : 0;
    },
  },
});
