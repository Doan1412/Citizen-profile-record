import vue from 'vue';

import Search from '@/components/Items/Search.vue';
import ButtonDownload from '@/components/Items/ButtonDownload.vue';
import Tableft from '@/components/Layouts/Tableft.vue';
import Navbar from '@/components/Layouts/Navbar.vue';
import Notification from '@/components/Notifications/Notification.vue';
import AddAccount from '@/components/Users/AddAccount.vue'
import FooterPage from "@/components/Layouts/FooterPage.vue";
import InforCitizenNew from '@/components/Users/InforCitizenNew.vue';
import Criminalrecord from '@/components/Users/Criminalrecord.vue';
import ListInforCitizen from '@/components/Users/ListInforCitizen.vue';
import InforPoli from '@/components/Users/InforPoli.vue';
import LandingPage from '@/components/LandingPages/LandingPage.vue';
import PopupConfirm from '@/components/Popup/PopupConfirm.vue';
import ListInfor6Colums from '@/components/Users/ListInfor6Colums.vue';
import ListInfor4Colums from '@/components/Users/ListInfor4Colums.vue';
import PopupAddReqAndApp from '@/components/Popup/PopupAddReqAndApp.vue';
import ButtonAdd from '@/components/Popup/ButtonAdd.vue';


vue.component('PopupConfirm',PopupConfirm)
vue.component('AddAccount',AddAccount)
vue.component('Tableft',Tableft)
vue.component('InforCitizenNew',InforCitizenNew)
vue.component('Criminalrecord',Criminalrecord)
vue.component('ListInforCitizen',ListInforCitizen)
vue.component('Search',Search)
vue.component('ButtonDownload',ButtonDownload)
vue.component('InforPoli',InforPoli)
vue.component('Navbar',Navbar)
vue.component('Notification', Notification)
vue.component('FooterPage',FooterPage)
vue.component('LandingPage',LandingPage)
vue.component('ListInfor4Colums',ListInfor4Colums)
vue.component('PopupAddReqAndApp',PopupAddReqAndApp)
vue.component('ButtonAdd',ButtonAdd)
vue.component('ListInfor6Colums',ListInfor6Colums)



