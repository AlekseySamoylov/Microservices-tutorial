package client

import (
	"fmt"
	"net/http"
	"log"
)

func StartApi(serverPort int, serverRegion string) {
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "Go Client region, %q", serverRegion)
	})

	log.Fatal(http.ListenAndServe(fmt.Sprintf(":%d", serverPort), nil))

}
