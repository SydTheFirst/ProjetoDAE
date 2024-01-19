
<template>
  <nav class="navbar navbar-expand-md navbar-dark bg-dark sticky-top flex-md-nowrap p-0 shadow">
    <div class="container-fluid">
      <router-link class="navbar-brand col-md-3 col-lg-2 me-0 px-3" :to="{ name: 'home' }" @click="clickMenuOption">
        <img src="/assets/logo.svg" alt="" width="30" height="24" class="d-inline-block align-text-top">
        VCARD
      </router-link>
      <button id="buttonSidebarExpandId" class="navbar-toggler" type="button" data-bs-toggle="collapse"
        data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-end">
        <ul class="navbar-nav">
          <li class="nav-item" v-show="!userStore.user">
            <router-link class="nav-link" :class="{ active: $route.name === 'NewUser' }" :to="{ name: 'NewUser' }"
              @click="clickMenuOption">
              <i class="bi bi-person-check-fill"></i>
              Register
            </router-link>
          </li>
          <li class="nav-item" v-show="!userStore.user">
            <router-link class="nav-link" :class="{ active: $route.name === 'Login' }" :to="{ name: 'Login' }"
              @click="clickMenuOption">
              <i class="bi bi-box-arrow-in-right"></i>
              Login
            </router-link>
          </li>
          <li class="nav-item dropdown" v-show="userStore.user">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
              data-bs-toggle="dropdown" aria-expanded="false">
              <img :src="userStore.userPhotoUrl" class="rounded-circle z-depth-0 avatar-img" alt="avatar image">
              <span class="avatar-text">{{ userStore.userName }}</span>
            </a>
            <ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
              <li>
                <router-link class="dropdown-item"
                  :class="{ active: $route.name == 'User' && $route.params.id == userStore.userId }"
                  :to="{ name: 'User', params: { id: userStore.userId } }" @click="clickMenuOption"
                  v-show="userStore.userType == 'V'">
                  <i class="bi bi-person-square"></i>
                  Profile
                </router-link>
              </li>
              <li>
                <router-link class="dropdown-item"
                  :class="{ active: $route.name == 'Admin' && $route.params.id == userStore.userId }"
                  :to="{ name: 'Admin', params: { id: userStore.userId } }" @click="clickMenuOption"
                  v-show="userStore.userType == 'A'">
                  <i class="bi bi-person-square"></i>
                  Profile
                </router-link>
              </li>
              <li>
                <router-link class="dropdown-item" :class="{ active: $route.name === 'ChangePassword' }"
                  :to="{ name: 'ChangePassword' }" @click="clickMenuOption">
                  <i class="bi bi-key-fill"></i>
                  Change password
                </router-link>
              </li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li>
                <a class="dropdown-item" @click.prevent="logout">
                  <i class="bi bi-arrow-right"></i>Logout
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container-fluid">
    <div class="row">
      <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
        <div class="position-sticky pt-3">
          <ul class="nav flex-column" v-if="userStore.user">
            <li class="nav-item">
              <router-link class="nav-link" :class="{ active: $route.name === 'Dashboard' }" :to="{ name: 'Dashboard' }"
                @click="clickMenuOption">
                <i class="bi bi-house"></i>
                Dashboard
              </router-link>
            </li>



            <li class="nav-item">
              <router-link class="nav-link" :class="{ active: $route.name === 'Users' }"
                v-show="userStore.userType === 'A'" :to="{ name: 'Users' }" @click="clickMenuOption">
                <i class="bi bi-files"></i>
                VCards
              </router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" :class="{ active: $route.name === 'Admins' }"
                v-show="userStore.userType === 'A'" :to="{ name: 'Admins' }" @click="clickMenuOption">
                <i class="bi bi-files"></i>
                Admins
              </router-link>
            </li>
            <li class="nav-item" v-show="userStore.userType == 'V'">
              <router-link class="nav-link" :class="{ active: $route.name === 'Transaction' }"
                :to="{ name: 'Transaction' }" @click="clickMenuOption">
                <i class="bi bi-bar-chart-line"></i>
                Criar Transação
              </router-link>
            </li>
            <li class="nav-item" v-show="userStore.userType == 'V'">
              <router-link class="nav-link" :class="{ active: $route.name === 'MyTransactions' }"
                :to="{ name: 'MyTransaction' }" @click="clickMenuOption">
                <i class="bi bi-bar-chart-line"></i>
                As minhas Transações
              </router-link>
            </li>
            <li class="nav-item" v-show="userStore.userType == 'V'">
              <router-link class="nav-link" :class="{ active: $route.name === 'MyCategories' }"
                :to="{ name: 'MyCategories' }" @click="clickMenuOption">
                <i class="bi bi-bar-chart-line"></i>
                As minhas Categorias
              </router-link>
            </li>
            <li class="nav-item" v-show="userStore.userType == 'V'">
              <router-link class="nav-link" :class="{ active: $route.name === 'CreateCategory' }"
                :to="{ name: 'CreateCategory' }" @click="clickMenuOption">
                <i class="bi bi-bar-chart-line"></i>
                Criar Categoria
              </router-link>
            </li>
            <li class="nav-item" v-show="userStore.userType == 'A'">
              <router-link class="nav-link" :class="{ active: $route.name === 'AdminStatistics' }"
                :to="{ name: 'AdminStatistics' }" @click="clickMenuOption">
                <i class="bi bi-bar-chart-line"></i>
                Statistics
              </router-link>
            </li>
            <li class="nav-item" v-show="userStore.userType == 'V'">
              <router-link class="nav-link" :class="{ active: $route.name === 'VCardStatistics' }"
                :to="{ name: 'VCardStatistics' }" @click="clickMenuOption">
                <i class="bi bi-bar-chart-line"></i>
                Statistics
              </router-link>
            </li>
            <li class="nav-item" v-show="userStore.userType == 'A'">
              <router-link class="nav-link" :class="{ active: $route.name === 'Reports' }" :to="{ name: 'Reports' }"
                @click="clickMenuOption">
                <i class="bi bi-bar-chart-line"></i>
                Reports
              </router-link>
            </li>
          </ul>




          <div class="d-block d-md-none">
            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>User</span>
            </h6>
            <ul class="nav flex-column mb-2">
              <li class="nav-item" v-show="!userStore.user">
                <router-link class="nav-link" :class="{ active: $route.name === 'NewUser' }" :to="{ name: 'NewUser' }"
                  @click="clickMenuOption">
                  <i class="bi bi-person-check-fill"></i>
                  Register
                </router-link>
              </li>
              <li class="nav-item" v-show="!userStore.user">
                <router-link class="nav-link" :class="{ active: $route.name === 'Login' }" :to="{ name: 'Login' }"
                  @click="clickMenuOption">
                  <i class="bi bi-box-arrow-in-right"></i>
                  Login
                </router-link>
              </li>
              <li class="nav-item dropdown" v-show="userStore.user">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink2" role="button"
                  data-bs-toggle="dropdown" aria-expanded="false">
                  <img :src="userStore.userPhotoUrl" class="rounded-circle z-depth-0 avatar-img" alt="avatar image">
                  <span class="avatar-text">{{ userStore.userName }}</span>
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink2">
                  <li>
                    <router-link class="dropdown-item"
                      :class="{ active: $route.name == 'User' && $route.params.id == userStore.userId }"
                      :to="{ name: 'User', params: { id: userStore.userId } }" @click="clickMenuOption">
                      <i class="bi bi-person-square"></i>
                      Profile
                    </router-link>
                  </li>
                  <li>
                    <router-link class="dropdown-item"
                      :class="{ active: $route.name == 'Admin' && $route.params.id == userStore.userId }"
                      :to="{ name: 'Admin', params: { id: userStore.userId } }" @click="clickMenuOption">
                      <i class="bi bi-person-square"></i>
                      Profile
                    </router-link>
                  </li>
                  <li>
                    <router-link class="dropdown-item" :class="{ active: $route.name === 'ChangePassword' }"
                      :to="{ name: 'ChangePassword' }" @click="clickMenuOption">
                      <i class="bi bi-key-fill"></i>
                      Change password
                    </router-link>
                  </li>
                  <li>
                    <hr class="dropdown-divider">
                  </li>
                  <li>
                    <a class="dropdown-item" @click.prevent="logout">
                      <i class="bi bi-arrow-right"></i>Logout
                    </a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>

        </div>
      </nav>

      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<style>
@import "./assets/dashboard.css";

.avatar-img {
  margin: -1.2rem 0.8rem -2rem 0.8rem;
  width: 3.3rem;
  height: 3.3rem;
}

.avatar-text {
  line-height: 2.2rem;
  margin: 1rem 0.5rem -2rem 0;
  padding-top: 1rem;
}

.dropdown-item {
  font-size: 0.875rem;
}

.btn:focus {
  outline: none;
  box-shadow: none;
}

#sidebarMenu {
  overflow-y: auto;
}
</style>
