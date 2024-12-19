<template>
  <header class="header">
    <div class="header__logo">
      <img
        src="http://localhost:8080/images/icons/choki-poki-logo.png"
        alt="choki-poki-logo"
      />
      <h2 class="header__logo__title">Choki-Poki</h2>
    </div>

    <nav class="header__nav">
      <ul class="header__nav-list">
        <li class="header__nav-item">
          <router-link class="header__nav-link" to="/home">Home</router-link>
        </li>
        <li class="header__nav-item header__nav-item--cart">
          <router-link class="header__nav-link" to="/shopping-cart">
            <img src="http://localhost:8080/images/icons/cart.svg" alt="cart" />
          </router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script setup></script>

<style lang="scss" scoped></style>
