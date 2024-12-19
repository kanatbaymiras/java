<template>
  <div class="card">
    <img class="card__image" :src="imageUrl" :alt="name" />
    <div class="card__footer">
      <h4 class="card__title">{{ name }}</h4>
      <span class="card__price">{{ price }}</span>
      <button class="card__button" @click="handleBuy">
        Add to cart <span v-if="quantity > 0">({{ quantity }} in cart)</span>
      </button>
    </div>
  </div>
</template>

<script setup>
import { useCartStore } from "@/store/cartStore";
import { computed } from "vue";

const cartStore = useCartStore();

const quantity = computed(() => cartStore.itemQuantityByName(props.name));

const props = defineProps({
  name: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
  imageUrl: {
    type: String,
    required: true,
  },
});

const emit = defineEmits(["buy"]);

const handleBuy = () => {
  const productDetails = {
    name: props.name,
    price: props.price,
  };
  emit("buy", productDetails);
};
</script>

<style lang="scss" scoped></style>
