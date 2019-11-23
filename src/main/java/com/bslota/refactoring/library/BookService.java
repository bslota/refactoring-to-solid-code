package com.bslota.refactoring.library;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final PatronRepository patronRepository;

    private final NotificationSender emailService;

    public BookService(BookRepository bookRepository, PatronRepository patronRepository, NotificationSender emailService) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.emailService = emailService;
    }

    boolean placeOnHold(int bookId, int patronId, int days) {
        Book book = bookRepository.findBy(BookId.of(bookId));
        Patron patron = patronRepository.findBy(PatronId.of(patronId));
        if (thereIsA(book) && thereIsA(patron)) {
            PlaceOnHoldResult result = patron.placeOnHold(book);
            if (result instanceof BookPlacedOnHold) {
                book.placedOnHold(patron.getPatronId(), days);
                addLoyaltyPoints(patron.getLoyalties());
                bookRepository.save(book);
                patronRepository.save(patron);
                addLoyaltyPoints(patron.getLoyalties());
                if (patron.getLoyalties().isQualifiesForFreeBook()) {
                    sendNotificationToEmployeesAboutFreeBookRewardFor(patron.getLoyalties());
                }
                return true;
            }
        }
        return false;
    }

    private void sendNotificationToEmployeesAboutFreeBookRewardFor(PatronLoyalties loyalties) {
        String title = "[REWARD] Patron for free book reward waiting";
        String body = "Dear Colleague, \n" +
                "One of our patrons with ID " + loyalties.getPatronId().asInt() + " gathered " + loyalties.getPoints() + ". \n" +
                "Please contact him and prepare a free book reward!";
        String employees = "customerservice@your-library.com";
        emailService.sendMail(new String[]{employees}, "contact@your-library.com", title, body);
    }

    private boolean thereIsA(Patron patron) {
        return patron != null;
    }

    private boolean thereIsA(Book book) {
        return book != null;
    }

    private void addLoyaltyPoints(PatronLoyalties loyalties) {
        int type = loyalties.getType();
        switch (type) {
            case 0: // regular patron
                loyalties.setPoints(loyalties.getPoints() + 1);
                break;
            case 1: // researcher
                loyalties.setPoints(loyalties.getPoints() + 5);
                break;
            case 2: //premium
                int newPoints;
                if (loyalties.getPoints() == 0) {
                    newPoints = 100;
                } else {
                    newPoints = loyalties.getPoints() * 2;
                }
                loyalties.setPoints(newPoints);
                break;
            default:
                break;
        }
        if (loyalties.getPoints() > 10000) {
            loyalties.setQualifiesForFreeBook(true);
        }
    }

}
