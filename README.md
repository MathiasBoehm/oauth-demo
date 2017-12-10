# Keys

## Generate Keypair
```bash
$ keytool -genkeypair -alias mykeys -keyalg RSA -keypass mypass -keystore mykeys.jks -storepass mypass
```
## Export Public Key
```bash
$ keytool -list -rfc --keystore mykeys.jks -storepass mypass | openssl x509 -inform pem -pubkey
```

# Flow

## Get Token
```bash
$ curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=password&username=user&password=password" | jq '.'
```

Result:

```json
{
  "jti": "1826e5e3-4d18-4f95-bf7e-5cc2f58d0841",
  "scope": "read write",
  "expires_in": 43199,
  "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyX2lkIl0sInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiMTgyNmU1ZTMtNGQxOC00Zjk1LWJmN2UtNWNjMmY1OGQwODQxIiwiZXhwIjoxNTE1MTc4MDk3LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiNmE5ZGE2YWEtZmNmYi00NWEwLWEwODEtNWViYzMxZGM1M2QxIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1hcHAifQ.BF4n7tHsRmaK3D6MWrS5gKZFVPhwBPUuCRgq_GC59QEQJQuNRc5i25fwfZ9tgw3PqfVH-vfVyEznYGt6A35dYjZDI9J3AvBH7TukPPebzct8gzEnvEku9kLYeBwuThULlWNjn4NHA5OmbqSamCYjEjUmVsulIA9jhA3cMgzTnIyjmsw0Bh94t--N6Eahy8CchF6AGgZIrxdhnBERVrcqmQZIsJGCT0ODybHqdCv5ztiYEX1QE2A0Xc-Y1FvIXvZEaYJ90hgzQLVaoJq4wdtU46IehLysfwQMaItiuOD0lFos7JUtZs0RzJc0TbZs8FamgGNm_W4_1ecOn1CYHDbZuA",
  "token_type": "bearer",
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyX2lkIl0sInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTEyNjI5Mjk3LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMTgyNmU1ZTMtNGQxOC00Zjk1LWJmN2UtNWNjMmY1OGQwODQxIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1hcHAifQ.KbAKmJ3K7JVWbUL5aZXHytp3B4PrLokYWsH6JIgA3huGZkNWAJaR_wffCDcXxDNtFrpEfI5buk1ZOGxwMPUmWAW_ZRk36nQPJLGrowDGBThZuIaPrnvcrxboc4oGqbXuPCVCnGEehHokm2Q38FXzNC6t3_MmpIRDx7AHpgMkBJcBuzqtr7hbE5WTA2IvCDQQyf1I5FH4b_FOCo1W_2YjPlZFRdqMadDmbh6dycuUyqENjQsIdtD4nqpGj-Mhzz-N7r8sj7ePy4UFlkeBFKVLZk9Nn7pCmFPDjXdxxeAijrRIfpWgFTyh475odkAk_Zt2AiWs-lGo1j3chFasbrvDfw"
}
```


# Do Client Call
```bash
$ TOKEN=$(curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=password&username=user&password=password" | jq '.access_token' | sed 's/"//g') 
$ curl -H "Authorization: Bearer $TOKEN" localhost:8081/demo
```