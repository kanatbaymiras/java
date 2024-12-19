<template>
  <div class="catalogue">
    <card
      v-for="item in store"
      :key="item.id"
      :imageUrl="'http://localhost:8080/images/' + item.name + '.jpg'"
      :name="item.name"
      :price="item.price + '$'"
      @buy="handleBuyEvent(item)"
    ></card>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useCartStore } from "@/store/cartStore";
import axios from "axios";
import Card from "./Card.vue";

const store = ref([]);
const cartStore = useCartStore();

onMounted(async () => {
  try {
    const { data } = await axios.get("http://localhost:8080/api/products");
    store.value = data;
  } catch (e) {
    console.error(e.stack);
  }
});

const handleBuyEvent = (item) => {
  cartStore.addItem(item);
  console.log("Item added to card: ", item.name);
};
</script>

<style lang="scss" scoped></style>
