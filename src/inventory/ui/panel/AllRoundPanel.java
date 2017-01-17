package inventory.ui.panel;

import java.awt.Color;

public class AllRoundPanel extends javax.swing.JPanel {

    public AllRoundPanel() {
        initComponents();
        upperlab.setText("Product");
        cardlayoutpanel.removeAll();
        cardlayoutpanel.add(new ProductPanel());
        cardlayoutpanel.repaint();
        cardlayoutpanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainpanel = new javax.swing.JPanel();
        upperpanel = new javax.swing.JPanel();
        productpanel = new javax.swing.JPanel();
        productlab = new javax.swing.JLabel();
        supplierpanel = new javax.swing.JPanel();
        supplierlab = new javax.swing.JLabel();
        brandpanel = new javax.swing.JPanel();
        brandlab = new javax.swing.JLabel();
        categorypanel = new javax.swing.JPanel();
        categorylab = new javax.swing.JLabel();
        upperlab = new javax.swing.JLabel();
        cardlayoutpanel = new javax.swing.JPanel();

        mainpanel.setBackground(new java.awt.Color(255, 255, 255));

        upperpanel.setBackground(new java.awt.Color(102, 102, 102));

        productpanel.setBackground(new java.awt.Color(102, 102, 102));
        productpanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        productpanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                productpanelMouseReleased(evt);
            }
        });

        productlab.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        productlab.setForeground(new java.awt.Color(255, 255, 255));
        productlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/inventory4.png"))); // NOI18N
        productlab.setText("Product");
        productlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                productlabMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout productpanelLayout = new javax.swing.GroupLayout(productpanel);
        productpanel.setLayout(productpanelLayout);
        productpanelLayout.setHorizontalGroup(
            productpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productlab, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        productpanelLayout.setVerticalGroup(
            productpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productlab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        supplierpanel.setBackground(new java.awt.Color(102, 102, 102));
        supplierpanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        supplierlab.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        supplierlab.setForeground(new java.awt.Color(255, 255, 255));
        supplierlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        supplierlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/supplier2.png"))); // NOI18N
        supplierlab.setText("Supplier");
        supplierlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                supplierlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                supplierlabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                supplierlabMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                supplierlabMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout supplierpanelLayout = new javax.swing.GroupLayout(supplierpanel);
        supplierpanel.setLayout(supplierpanelLayout);
        supplierpanelLayout.setHorizontalGroup(
            supplierpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(supplierlab, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        supplierpanelLayout.setVerticalGroup(
            supplierpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(supplierlab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        brandpanel.setBackground(new java.awt.Color(102, 102, 102));
        brandpanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        brandlab.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        brandlab.setForeground(new java.awt.Color(255, 255, 255));
        brandlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        brandlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/brand1.png"))); // NOI18N
        brandlab.setText("Brand");
        brandlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                brandlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                brandlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                brandlabMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout brandpanelLayout = new javax.swing.GroupLayout(brandpanel);
        brandpanel.setLayout(brandpanelLayout);
        brandpanelLayout.setHorizontalGroup(
            brandpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(brandlab, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        brandpanelLayout.setVerticalGroup(
            brandpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(brandlab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        categorypanel.setBackground(new java.awt.Color(102, 102, 102));
        categorypanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        categorylab.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        categorylab.setForeground(new java.awt.Color(255, 255, 255));
        categorylab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        categorylab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/category.png"))); // NOI18N
        categorylab.setText("Category");
        categorylab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categorylabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categorylabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                categorylabMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout categorypanelLayout = new javax.swing.GroupLayout(categorypanel);
        categorypanel.setLayout(categorypanelLayout);
        categorypanelLayout.setHorizontalGroup(
            categorypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(categorylab, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
        );
        categorypanelLayout.setVerticalGroup(
            categorypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(categorylab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        upperlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        upperlab.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upperlab, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                .addComponent(productpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(supplierpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(brandpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(categorypanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperpanelLayout.createSequentialGroup()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(supplierpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(brandpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(categorypanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(upperlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        cardlayoutpanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardlayoutpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(upperpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(upperpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardlayoutpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void categorylabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabMouseReleased
        upperlab.setText("Category");
        cardlayoutpanel.removeAll();
        cardlayoutpanel.add(new CategoryPanel());
        cardlayoutpanel.repaint();
        cardlayoutpanel.revalidate();
    }//GEN-LAST:event_categorylabMouseReleased

    private void categorylabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabMouseExited
        categorylab.setForeground(Color.white);
    }//GEN-LAST:event_categorylabMouseExited

    private void categorylabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabMouseEntered
        categorylab.setForeground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_categorylabMouseEntered

    private void brandlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brandlabMouseReleased
        upperlab.setText("Brand");
        cardlayoutpanel.removeAll();
        cardlayoutpanel.add(new BrandPanel());
        cardlayoutpanel.repaint();
        cardlayoutpanel.revalidate();
    }//GEN-LAST:event_brandlabMouseReleased

    private void brandlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brandlabMouseExited
        brandlab.setForeground(Color.white);
    }//GEN-LAST:event_brandlabMouseExited

    private void brandlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brandlabMouseEntered
        brandlab.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_brandlabMouseEntered

    private void supplierlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierlabMouseReleased
        upperlab.setText("Supplier");
        cardlayoutpanel.removeAll();
        cardlayoutpanel.add(new SupplierPanel());
        cardlayoutpanel.repaint();
        cardlayoutpanel.revalidate();
    }//GEN-LAST:event_supplierlabMouseReleased

    private void supplierlabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierlabMousePressed

    }//GEN-LAST:event_supplierlabMousePressed

    private void supplierlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierlabMouseExited
        supplierlab.setForeground(Color.white);
    }//GEN-LAST:event_supplierlabMouseExited

    private void supplierlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierlabMouseEntered
        supplierlab.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_supplierlabMouseEntered

    private void productpanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productpanelMouseReleased

    }//GEN-LAST:event_productpanelMouseReleased

    private void productlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productlabMouseReleased
        upperlab.setText("Product");
        cardlayoutpanel.removeAll();
        cardlayoutpanel.add(new ProductPanel());
        cardlayoutpanel.repaint();
        cardlayoutpanel.revalidate();
    }//GEN-LAST:event_productlabMouseReleased

    private void productlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productlabMouseExited
        productlab.setForeground(Color.white);
    }//GEN-LAST:event_productlabMouseExited

    private void productlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productlabMouseEntered
        productlab.setForeground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_productlabMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandlab;
    private javax.swing.JPanel brandpanel;
    private javax.swing.JPanel cardlayoutpanel;
    private javax.swing.JLabel categorylab;
    private javax.swing.JPanel categorypanel;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JLabel productlab;
    private javax.swing.JPanel productpanel;
    private javax.swing.JLabel supplierlab;
    private javax.swing.JPanel supplierpanel;
    private javax.swing.JLabel upperlab;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
