<template>
  <main id="AptInfo-wrapper">
    <h3>매물 정보</h3>
    <article id="AptInfo-content" class="container-fluid">
      <div class="mb-3 left" id="aptInfo-btn">
        <a v-b-toggle href="#aptInfo-collapse" @click.prevent
          ><button class="learn-more">
            <span class="circle" aria-hidden="true">
              <span class="icon arrow"></span>
            </span>
            <span class="button-text"></span>
          </button></a
        >
      </div>
      <article id="AptInfo-article">
        <b-collapse id="aptInfo-collapse">
          <router-view />
        </b-collapse>
        <apt-info-map id="AptInfo-map" />
      </article>
    </article>
  </main>
</template>

<script>
import AptInfoMap from "@/components/aptinfo/AptInfoMap.vue";

export default {
  components: {
    AptInfoMap,
  },
};
</script>

<style lang="scss" scoped>
#AptInfo-wrapper {
  display: block;
  height: 100%;
  padding: 20px 0;
}
#aptInfo-btn{
  position: absolute;
  z-index: 3;
  padding: 20px;
}

#AptInfo-content {
  padding: 20px 0;
  height: 100vh;
}

#aptInfo-collapse {
  width: 400px;
  height: 100%;
  position: absolute;
  z-index: 2;
}

#AptInfo-article {
  width: 100%;
  height: 100%;
}

#AptInfo-map {
  width: 100%;
  height: 85vh;
}

$bg: #f3f8fa;
$white: #fff;
$black: #282936;

@mixin transition(
  $property: all,
  $duration: 0.45s,
  $ease: cubic-bezier(0.65, 0, 0.076, 1)
) {
  transition: $property $duration $ease;
}

* {
  box-sizing: border-box;
  &::before,
  &::after {
    box-sizing: border-box;
  }
}

button {
  position: relative;
  display: inline-block;
  cursor: pointer;
  outline: none;
  border: 0;
  vertical-align: middle;
  text-decoration: none;
  background: transparent;
  padding: 0;
  font-size: inherit;
  font-family: inherit;
  &.learn-more {
    width: 5rem;
    height: auto;
    .circle {
      @include transition(all, 0.45s, cubic-bezier(0.65, 0, 0.076, 1));
      position: relative;
      display: block;
      margin: 0;
      width: 3rem;
      height: 3rem;
      background: $black;
      border-radius: 1.625rem;
      .icon {
        @include transition(all, 0.45s, cubic-bezier(0.65, 0, 0.076, 1));
        position: absolute;
        top: 0;
        bottom: 0;
        margin: auto;
        background: $white;
        &.arrow {
          @include transition(all, 0.45s, cubic-bezier(0.65, 0, 0.076, 1));
          left: 0.625rem;
          width: 1.125rem;
          height: 0.125rem;
          background: none;
          &::before {
            position: absolute;
            content: "";
            top: -0.25rem;
            right: 0.0625rem;
            width: 0.625rem;
            height: 0.625rem;
            border-top: 0.125rem solid #fff;
            border-right: 0.125rem solid #fff;
            transform: rotate(45deg);
          }
        }
      }
    }
    .button-text {
      @include transition(all, 0.45s, cubic-bezier(0.65, 0, 0.076, 1));
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      padding: 0.75rem 0;
      margin: 0 0 0 1.85rem;
      color: $black;
      font-weight: 700;
      line-height: 1.6;
      text-align: center;
      text-transform: uppercase;
    }
  }
  &:hover {
    .circle {
      width: 70px;
      .icon {
        &.arrow {
          background: $white;
          transform: translate(1rem, 0);
        }
      }
    }
    .button-text {
      color: $white;
    }
  }
}

</style>
