
package com.healthner.healthner.controller.dto;

import com.healthner.healthner.domain.Purchase;
import com.healthner.healthner.domain.Reservation;
import com.healthner.healthner.domain.Trainer;
import com.healthner.healthner.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ReservationDto {

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    @ToString
    public static class ReservRequest {
        private String title;
        private Long id;

        @DateTimeFormat(pattern ="yyyy-MM-dd")
        private LocalDate date;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime startTime;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime endTime;

        @Nullable
        private User user;
        @Nullable
        private Trainer trainer;
        @Nullable
        private Purchase purchase;

        //예약을 dto로 받고 이를  reservation 객체로 생성해줌
        public Reservation toEntity(ReservationDto.ReservRequest reservRequest) {
            return Reservation.createReservation(reservRequest.getDate(), reservRequest.getStartTime(),
                    reservRequest.getEndTime(), reservRequest.getUser(), reservRequest.getTrainer(),
                    reservRequest.getPurchase());
        }
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    public static class ReservResponse {
        private String title;
        private Long id;
        private LocalDate date;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        @Nullable
        private User user;
        @Nullable
        private Trainer trainer;
        @Nullable
        private Purchase purchase;


        //예약목록에서 dto와 비교하여 client에게 제공
        public ReservResponse(Reservation reservation) {
            this.id = reservation.getId();
            this.date = reservation.getDate();
            this.startTime = reservation.getStartTime();
            this.endTime = reservation.getEndTime();
        }
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    public static class ReservToCal {
        private String title;
        private LocalDate start; //  calendar 상에서 날짜를 의미 :start

        //예약을 가공해서 calendar에 뿌려줄 수 있게끔
        public ReservToCal(Reservation reservation) {
            this.title = reservation.getTrainer().getUser().getName();
            this.start = reservation.getDate();
        }
    }

}




