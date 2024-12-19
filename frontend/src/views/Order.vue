<template>
  <div class="page--order">
    <Header />
    <Wrapper>
      <div class="shopping-cart">
        <h1>Order</h1>

        <div v-if="cartItems.length">
          <div v-for="item in cartItems" :key="item.id" class="cart-item">
            <h3>{{ item.name }}</h3>
            <p>Price: ${{ item.price.toFixed(2) }}</p>
            <p>Subtotal: ${{ (item.price * item.quantity).toFixed(2) }}</p>

            <div class="quantity-controls">
              <button
                @click="decreaseQuantity(item.id)"
                :disabled="item.quantity <= 1"
              >
                -
              </button>
              <span>{{ item.quantity }}</span>
              <button @click="increaseQuantity(item.id)">+</button>
            </div>
          </div>

          <div class="cart-summary">
            <h2>Total Items: {{ totalItems }}</h2>
            <h2>Total Price: ${{ totalPrice.toFixed(2) }}</h2>
            <button @click="clearCart">Clear Cart</button>
            <button
              @click="makeOrder"
              :disabled="isOrderProcessing || totalItems === 0"
            >
              Place Order
            </button>
          </div>
        </div>

        <p v-else>Your cart is empty. Add some products!</p>
      </div>
    </Wrapper>
  </div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useCartStore } from "@/store/cartStore";
import Header from "@/layout/Header.vue";
import Wrapper from "@/components/Wrapper.vue";
import axios from "axios";

const cartStore = useCartStore();

const cartItems = computed(() => cartStore.items);
const totalPrice = computed(() => cartStore.totalPrice);
const totalItems = computed(() => cartStore.totalItems);

const isOrderProcessing = ref(false);

const increaseQuantity = (id) => {
  const item = cartStore.items.find((item) => item.id === id);
  if (item) {
    cartStore.updateQuantity(id, item.quantity + 1);
  }
};

const decreaseQuantity = (id) => {
  const item = cartStore.items.find((item) => item.id === id);
  if (item && item.quantity > 1) {
    cartStore.updateQuantity(id, item.quantity - 1);
  }
};

const clearCart = () => {
  cartStore.clearCart();
};
const makeOrder = async () => {
  try {
    isOrderProcessing.value = true;

    const orderData = {
      products: cartStore.items.map((item) => ({
        id: item.id,
        quantityInOrder: item.quantity,
      })),
      totalPrice: cartStore.totalPrice,
      totalItems: cartStore.totalItems,
    };

    const response = await axios.post(
      "http://localhost:8080/api/orders",
      orderData
    );

    if (response.status === 200) {
      cartStore.clearCart();
      alert("Order placed successfully!");
    } else {
      alert("Order failed. Please try again.");
    }
  } catch (error) {
    console.error("Order submission failed: ", error);
    alert("There was an error placing your order. Please try again.");
  } finally {
    isOrderProcessing.value = false;
  }
};
</script>

<style lang="scss" scoped>
.shopping-cart {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.cart-item {
  display: grid;
  grid-template-columns: 1fr 1fr auto;
  align-items: center;
  gap: 10px;
  border-bottom: 1px solid #ccc;
  padding: 10px 0;
}

.cart-item h3 {
  grid-column: span 2;
  margin: 0;
}

.quantity-controls {
  display: flex;
  align-items: center;
  gap: 10px;
}

.quantity-controls button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
}

.quantity-controls button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.cart-summary {
  margin-top: 20px;
  text-align: center;
}

.cart-summary h2 {
  margin: 10px 0;
}

button {
  padding: 8px 15px;
  font-size: 16px;
  cursor: pointer;
}

.cart-summary button[disabled] {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
