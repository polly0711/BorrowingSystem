### 一些設定
---

application.properties 設定資料庫名稱、使用者名稱、密碼
![properties](https://imgur.com/g52V9fH.png)

table 用 @Entity 自動生成，只要新增書籍、庫存資料  
SQL 檔案在 DB 資料夾內
***
首頁畫面：還未登入無法借書
![home](https://imgur.com/Gw15HP8.png)  

登入畫面：可判斷帳號是否被註冊過
![login](https://imgur.com/lEfbkfn.png)  

登入成功alert、判斷帳密是否正確  
![success](https://imgur.com/NkK4QYw.png)
![error](https://imgur.com/XB11oQ1.png)

登入後才可借書
![loginhome](https://imgur.com/P47LC3M.png)

點擊書名可查看書籍詳細資料  
![borrowed](https://imgur.com/UYufQe1.png)
狀態為已借閱無法借書，狀態為可借閱才可借書 
![available](https://imgur.com/AslWgJd.png)

點擊借書，可至我的書房查看已借書籍
![mybooks](https://imgur.com/Y3Xqkuh.png)

並且書籍狀態會改為已借閱
![b](https://imgur.com/bG5kReB.png)

&emsp;  
&emsp;  
點擊我要還書可使書籍狀態改回可借閱

