<template>
  <div class="page--home">
    <Header />
    <wrapper>
      <Catalogue />
    </wrapper>
  </div>
</template>

<script setup>
import Catalogue from "@/components/Catalogue.vue";
import Header from "@/layout/Header.vue";
import Wrapper from "@/components/Wrapper.vue";
</script>

<style lang="scss" scoped></style>
